(ns nukr.controller-test
  (:require [clojure.test :refer :all]
            [nukr.controller :as c]
            [nukr.db.profile :as p]))

(use-fixtures
  :each
  (fn [f]
    (reset! p/profiles {})
    (reset! p/friendships {})
    (f)))

(def ^:private user {:enable_friends_recommendation true,
                     :address {:city "string",
                               :country "string",
                               :region "string",
                               :state "string",
                               :street "string",
                               :zipcode "string"},
                     :email "string",
                     :first_name "string",
                     :name "joao",
                     :short_name "string",
                     :last_name "string",
                     :gender "string",
                     :languages ["string"],
                     :about "string"})

(def ^:private friend {:friend {:id 2}})

(deftest test-should-create-user
  (let [result (c/create-user user)]
    (is (=
         1 (get result :id)))))

(deftest test-should-get-all-users
  (c/create-user user)
  (c/create-user (assoc-in user [:email] "email@email.com"))
  (let [users (c/all-users)]
    (is (=
         2 (count users)))))

(deftest test-should-get-user-by-id
  (c/create-user user)
  (let [user (c/get-user-by-id 1)]
    (is (=
         "joao" (get user :name)))))

(deftest test-should-update-user
  (c/create-user user)
  (c/update-user 1 (assoc-in user [:name] "pedro"))
  (let [result (c/get-user-by-id 1)]
    (is (=
         "pedro" (get result :name)))))

(deftest test-should-delete-user-by-id
  (c/create-user user)
  (c/delete-user 1)
  (let [user (c/get-user-by-id 1)]
    (is (=
         nil user))))

(deftest test-should-add-friend
  (c/create-user user)
  (c/create-user (assoc-in user [:name] "francisco"))
  (let [friends (c/add-friend 1 2)
        expected {:user-id 1 :name "joao" :friends '({:id 2 :name "francisco" :can-recommendation true})}]
    (is (=
         expected friends))))

(deftest test-should-remove-friend
  (c/create-user user)
  (c/create-user (assoc-in user [:name] "francisco"))
  (c/add-friend 1 2)
  (c/remove-friend 1 2)
  (let [friends (c/get-friendship-by-use-id 1)
        expected {:user-id 1, :name "joao", :friends '()}]
    (is (=
         expected friends))))

(deftest test-should-get-all-friends-by-user-id
  (c/create-user user)
  (c/create-user (assoc-in user [:name] "francisco"))
  (c/create-user (assoc-in user [:name] "xablau"))
  (c/add-friend 1 2)
  (c/add-friend 1 3)
  (let [friends (c/get-friendship-by-use-id 1)
        expected {:user-id 1, :name "joao", :friends '({:id 3, :name "xablau", :can-recommendation true} {:id 2, :name "francisco", :can-recommendation true})}]
    (is (=
         expected friends))))

(deftest test-should-recomendation-friend-by-user-id
  (c/create-user user)
  (c/create-user (assoc-in user [:name] "francisco"))
  (c/create-user (assoc-in user [:name] "xabla2"))
  (c/add-friend 1 2)
  (c/add-friend 2 3)
  (let [recomendation (c/recommendation-friends 1)
        expected {:user-id 1, :name "joao", :recommendations '({:id 3, :name "xabla2", :can-recommendation true})}]
    (is (=
         expected recomendation))))

(deftest test-should-recommendation-not-auto-recommendation
  (c/create-user user)
  (c/create-user (assoc-in user [:name] "francisco"))
  (c/create-user (assoc-in user [:name] "xabla2"))
  (c/add-friend 1 2)
  (c/add-friend 2 3)
  (c/add-friend 2 1)
  (let [recomendation (c/recommendation-friends 1)
        expected {:user-id 1, :name "joao", :recommendations '({:id 3, :name "xabla2", :can-recommendation true})}]
    (is (=
         expected recomendation))))
