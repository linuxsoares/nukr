(ns nukr.api.recommendation
  (:require [pedestal-api
             [helpers :refer [handler]]]
            [nukr.controller :as controller]
            [schema.core :as s]
            [nukr.api.schema :as schema]))

(def recommendations
  "Find recommendations for user"
  (handler
   :recommendations
   {:summary "Friendship recommendation for users by id"
    :parameters {:path-params {:id s/Int}}
    :responses {200 {:body schema/RecommendationResp}}}
   (fn [request]
     (let [user (:path-params request)]
       {:status 200
        :body (controller/recommendation-friends (:id user))}))))
