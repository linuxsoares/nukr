(ns nukr.api.friendship
  (:require [pedestal-api
             [helpers :refer [before defbefore defhandler handler]]]
            [schema.core :as s]
            [nukr.api.schema :as schema]
            [nukr.controller :as controller]))

(def friendships
  "Get all friendships for user id"
  (handler
   :all-friends
   {:summary "Get all friendships for user id"
    :parameters {:path-params {:id s/Int}}
    :responses {200 {:body schema/FriendShipResp}}}
   (fn [request]
     (let [id (:path-params request)]
       {:status 200
        :body (controller/get-friendship-by-use-id (:id id))}))))

(def add-friend
  "Add friend"
  (handler
   :add-friend
   {:summary "Create friendship between users"
    :parameters {:path-params {:id s/Int}
                 :body-params schema/Friend}
    :responses {201 {:body schema/FriendShipResp}}}
   (fn [request]
     (let [user (:path-params request)
           friend (:body-params request)]
       (if-let [exist-user (controller/get-user-by-id (get-in friend [:friend :id]))]
         {:status 201
          :body (controller/add-friend (:id user) (get-in friend [:friend :id]))}
         {:status 404
          :body {:message (str "User " (get-in friend [:friend :id]) " not found")}})))))

(def remove-friend
  "Remove friendship between users"
  (handler
   :remove-friend
   {:summary "Remove friendship between users"
    :parameters {:path-params {:id s/Int}
                 :body-params schema/Friend}
    :responses {204 {:body s/Str}}}
   (fn [request]
     (let [user (:path-params request)
           friend (:body-params request)]
       (controller/remove-friend (:id user) (get-in friend [:friend :id]))
       {:status 204
        :body (str "Removed friendship")}))))
