(ns nukr.service-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [nukr.service :as service]
            [clojure.data.json :as json]))

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(deftest test-api-crud
  (let [body {
              :enable_friends_recommendation true,
              :address {
                        :city "string",
                        :country "string",
                        :region "string",
                        :state "string",
                        :street "string",
                        :zipcode "string"
                        },
              :email "string",
              :first_name "string",
              :name "string",
              :short_name "string",
              :last_name "string",
              :gender "string",
              :languages [
                          "string"
                          ],
              :about "string"
              }
        response (response-for service :post "/users/"
                               :headers {"Content-Type" "application/json"}
                               :body (json/write-str body))]
    (is (=
          201 (:status response)))
    (println response)
    (is (=
          1 (get (json/read-str (:body response)) "id")))))