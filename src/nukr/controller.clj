(ns nukr.controller
  (:require [nukr.db.profile :as profile]
            [nukr.logic :as logic]
            [clojure.set :as set]))

(defn create-user [user]
  (profile/create-user user))

(defn all-users []
  (profile/all-profile))

(defn get-user-by-id [id]
  (profile/get-user-by-id id))

(defn update-user [id user]
  (profile/update-user id user))

(defn delete-user [id]
  (profile/delete-user id))

(defn get-friendship-by-use-id [id]
  (let [friends (profile/get-friendships-by-use-id id)]
    (logic/format-friendships
     (get-user-by-id id) (map #(get-user-by-id %) (get friends id)))))

(defn add-friend [user-id friend-id]
  (let [friends (profile/add-friend user-id friend-id)]
    (logic/format-friendships
     (get-user-by-id user-id) (map #(get-user-by-id %) (get-in friends [user-id user-id])))))

(defn remove-friend [user-id friend-id]
  (profile/remove-friend user-id friend-id))

(defn recommendation-friends [user-id]
  (let [recommendations (logic/recommendations @profile/friendships user-id)
        users (map #(get-user-by-id %) recommendations)
        enabled-users-recommendations (logic/remove-user-recommendation-disabled users)]
    (println enabled-users-recommendations)
    (logic/format-recommendations
     (get-user-by-id user-id) enabled-users-recommendations)))
