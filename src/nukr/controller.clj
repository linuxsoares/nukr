(ns nukr.controller
  (:require [nukr.db.profile :as profile]
            [nukr.logic :as logic]
            [clojure.set :as set]))

(defn ^:private get-friends [data id]
  (get data id))

(defn can-recomendation-friend? [user]
  (get user :enable_friends_recommendation))

(defn ^:private recommendation [data id]
  (let [friends (get-friends data id)
        find (get friends id)
        recommendation (map #(get-friends data %) find)
        clear-recommendations (reduce into (remove nil? recommendation))]
    (-> (vals clear-recommendations)
        (flatten)
        (set)
        (set/difference (set (get friends id)))
        (disj id))))

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
  (let [recommendations (recommendation @profile/friendships user-id)]
    (logic/format-recommendations
     (get-user-by-id user-id) (map #(get-user-by-id %) recommendations))))
