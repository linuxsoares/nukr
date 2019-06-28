(ns nukr.api.recommendation
  (:require [pedestal-api
             [core :as api]
             [helpers :refer [before defbefore defhandler handler]]]
            [nukr.controller :as controller]
            [schema.core :as s]
            [nukr.http.schema :as schema]))

(def recommendations
  "Find recommendations for user"
  (handler
   :recommendations
   {:summary "Recomendação de amizade para usuários por id"
    :parameters {:path-params {:id s/Int}}
    :responses {200 {:body schema/RecommendationResp}}}
   (fn [request]
     (let [user (:path-params request)]
       {:status 200
        :body (controller/recommendation-friends (:id user))}))))
