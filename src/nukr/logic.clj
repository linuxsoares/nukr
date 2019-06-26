(ns nukr.logic
  (:require [clojure.set :as set]))

(defn ^:private format-friendship [friend]
  {:id (:id friend) :name (:name friend) :can-recommendation (:enable_friends_recommendation friend)})

(defn format-friendships [user friends]
  {:user-id  (:id user)
   :name (:name user)
   :friends (map format-friendship friends)})

(defn format-recommendations [user friends]
  {:user-id  (:id user)
   :name (:name user)
   :recommendations (map format-friendship friends)})

(defn can-recomendation-friend? [user]
  (get user :enable_friends_recommendation))

(defn get-friends [data id]
  (let [user (get data id)]
    (get user id)))

(defn recommendations [data recommendation ids remove-id]
  (if (not-empty ids)
    (let [friends (get-friends data (first ids))
          find (remove #{remove-id} (distinct (concat (rest ids) recommendation)))]
      (recur data (concat friends recommendation) find (concat (first ids) remove-id)))
    recommendation))

(defn recommendation [data id]
  (recommendations data nil (get-friends data id) nil))

(def data {1 {1 '(2), :id 1}, 2 {2 '(1 3), :id 2}, 3 {3 '(1 4 5), :id 3}})
