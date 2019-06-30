(ns nukr.service-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [nukr.service :as service]
            [clojure.data.json :as json]
            [nukr.db.profile :as p]
            [nukr.db.friendship :as f]
            [nukr.controller :as c]))

(use-fixtures
  :each
  (fn [f]
    (reset! p/profiles {})
    (reset! f/friendships {})
    (f)))

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(def ^:private payload {:enable_friends_recommendation true,
                        :address {:city "string",
                                  :country "string",
                                  :region "string",
                                  :state "string",
                                  :street "string",
                                  :zipcode "string"},
                        :email "linux.soares@gmail.com",
                        :birthday "2019-01-01"
                        :first_name "string",
                        :name "joao",
                        :short_name "string",
                        :last_name "string",
                        :gender "string",
                        :languages ["string"],
                        :about "string"})

(defn ^:private payload-friend [friend-id]
  (json/write-str {:friend {:id friend-id}}))

(defn ^:private get-user
  ([] (get-user nil))
  ([user-id]
   (let [uri (if user-id
               (str "/users/" user-id)
               "/users/")]
     (response-for service :get uri
                   :headers {"Content-Type" "application/json"}))))

(defn ^:private post-user [payload]
  (response-for service :post "/users/"
                :headers {"Content-Type" "application/json"}
                :body (json/write-str payload)))

(defn ^:private delete-user [user-id]
  (response-for service :delete (str "/users/" user-id)
                :headers {"Content-Type" "application/json"}))

(defn ^:private put-user
  ([user-id payload] (put-user user-id payload nil nil))
  ([user-id payload key change]
   (response-for service :put (str "/users/" 1)
                 :headers {"Content-Type" "application/json"}
                 :body (json/write-str (if (and key change)
                                         (assoc-in payload [key] change)
                                         payload)))))

(deftest test-should-get-all-users
  (let [response (get-user)]
    (is (>
         (count response) 0))))

(deftest test-api-create-user
  (let [response (post-user payload)]
    (is (=
         201 (:status response)))
    (is (=
         1 (get (json/read-str (:body response)) "id")))))

(deftest test-api-should-create-user-error-user-exists
  (c/create-user (assoc-in payload [:email] "joao@gmail.com"))
  (let [response (post-user (assoc-in payload [:email] "joao@gmail.com"))
        expected {"message" "User with email: joao@gmail.com exists!"}]
    (is (=
         409 (:status response)))
    (is (=
         expected (json/read-str (:body response))))))

(deftest test-api-get-user-by-id
  (post-user payload)
  (let [user-id 1
        response (get-user user-id)]
    (is (=
         200 (:status response)))
    (is (=
         "joao" (get (json/read-str (:body response)) "name")))))

(deftest test-api-should-update-user-by-id
  (c/create-user (assoc-in payload [:email] "joana@joana.com"))
  (post-user (assoc-in payload [:email] "joana@joana.com"))
  (put-user 1 (assoc-in payload [:email] "joana@joana.com") :name "maria")
  (let [name "maria"
        user-id 1
        response (get-user user-id)]
    (is (=
         200 (:status response)))
    (is (=
         name (get (json/read-str (:body response)) "name")))))

(deftest test-api-should-delete-user
  (c/create-user payload)
  (let [user-id 1
        response (get-user user-id)]
    (is (=
         200 (:status response)))
    (is (=
         1 (get (json/read-str (:body response)) "id")))
    (delete-user user-id))
  (let [user-id 1
        response (get-user user-id)]
    (is (=
         404 (:status response)))))

(deftest test-api-should-add-friend
  (c/create-user payload)
  (c/create-user (assoc-in payload [:email] "francisco@francisco.com"))
  (let [user-id 1
        friend-id 2
        response (response-for service :post (str "/users/" user-id "/friendships")
                               :headers {"Content-Type" "application/json"}
                               :body (payload-friend friend-id))
        expected {"user-id" 1, "name" "joao", "friends" [{"id" 2, "name" "joao", "can-recommendation" true}]}]
    (is (=
         expected (json/read-str (:body response))))))

(deftest test-api-should-not-found-friend
  (c/create-user payload)
  (let [user-id 1
        friend-id 10
        response (response-for service :post (str "/users/" user-id "/friendships")
                               :headers {"Content-Type" "application/json"}
                               :body (payload-friend friend-id))
        expected {"message" (str "User " friend-id " not found")}]
    (is (=
         expected (json/read-str (:body response))))
    (is (=
         404 (:status response)))))

(deftest test-api-should-remove-friend
  (c/create-user payload)
  (c/create-user (assoc-in payload [:name] "francisco"))
  (let [user-id 1
        friend-id 2
        response (response-for service :delete (str "/users/" user-id "/friendships")
                               :headers {"Content-Type" "application/json"}
                               :body (payload-friend friend-id))
        expected "Removed friendship"]
    (is (=
         expected (:body response)))
    (is (=
         204 (:status response)))))

(deftest test-api-should-get-friendship-by-use-id
  (c/create-user payload)
  (c/create-user (assoc-in payload [:name] "francisco"))
  (c/add-friend 1 2)
  (let [user-id 1
        response (response-for service :get (str "/users/" user-id "/friendships")
                               :headers {"Content-Type" "application/json"})
        expected {"user-id" 1, "name" "joao", "friends" [{"id" 2, "name" "francisco", "can-recommendation" true}]}]
    (is (=
         200 (:status response)))
    (is (=
         expected (json/read-str (:body response))))))

(deftest test-should-return-friend-recommendation
  (c/create-user payload)
  (c/create-user (assoc-in payload [:name] "francisco"))
  (c/create-user (assoc-in payload [:name] "maria"))
  (c/add-friend 1 2)
  (c/add-friend 2 3)
  (let [user-id 1
        response (response-for service :get (str "/users/" user-id "/recommendations"))
        expected {"user-id" 1, "name" "joao", "recommendations" [{"id" 3, "name" "maria", "can-recommendation" true}]}]
    (is (=
         200 (:status response)))
    (is (=
         expected (json/read-str (:body response))))))
