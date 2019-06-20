(ns nukr.api.profile
  (:require [io.pedestal.interceptor.chain :refer [terminate]]
            [io.pedestal.interceptor :refer [interceptor]]
            [pedestal-api
             [core :as api]
             [helpers :refer [before defbefore defhandler handler]]]
            [schema.core :as s]
            [nukr.controller :as controller]
            [nukr.http.schema :as schema]))

(def all-users
  "Get all users from Nukr"
  (api/annotate
    {:summary "Get all users from Nukr"
     :parameters {:query-params {(s/optional-key :sort) (s/enum :asc :desc)}}
     :responses {200 {:body {:users [schema/UserResp]}}}}
    (interceptor
      {:name :all-users
       :enter (fn [ctx]
                (assoc ctx :response
                           {:status 200
                            :body {:users (controller/all-users)}}))})))

(def create-user
  "Create a User"
  (handler
    :create-user
    {:summary "Create a User"
     :parameters {:body-params schema/User}
     :responses {201 {:body schema/UserResp}}}
    (fn [request]
      (let [user (:body-params request)]
        {:status 201
         :body (controller/create-user user)}))))

(defbefore load-user
           {:summary    "Load a user by id"
            :parameters {:path-params {:id s/Int}}
            :responses  {404 {:body s/Str}}}
           [{:keys [request] :as context}]
           (if-let [user (controller/get-user-by-id (get-in request [:path-params :id]))]
             (update context :request assoc :user user)
             (-> context terminate (assoc :response {:status 404
                                                     :body "No user found with this id"}))))

(defhandler get-user
            {:summary     "Get a user by id"
             :parameters  {:path-params {:id s/Int}}
             :responses   {200 {:body schema/UserResp}
                           404 {:body s/Str}}}
            [{:keys [user] :as request}]
            {:status 200
             :body user})

(def update-user
  "Update a user by id"
  (before
    ::update-user
    {:summary     "Update a user by id"
     :parameters  {:path-params {:id s/Int}
                   :body-params schema/User}
     :responses   {200 {:body s/Str}}}
    (fn [{:keys [request]}]
      (controller/update-user
        (get-in request [:path-params :id]) (:body-params request))
      {:status 200
       :body "User updated"})))

(def delete-user
  "Delete a user by id"
  (api/annotate
    {:summary     "Delete a user by id"
     :parameters  {:path-params {:id s/Int}}
     :responses   {204 {:body s/Str}}}
    (interceptor
      {:name  ::delete-user
       :enter (fn [ctx]
                (let [user (get-in ctx [:request :user])]
                  (controller/delete-user (:id user))
                  (assoc ctx :response
                             {:status 204
                              :body (str "Deleted " (:name user))})))})))
