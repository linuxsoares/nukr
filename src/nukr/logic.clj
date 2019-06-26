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

(defn ^:private get-friends [data id]
  (get data id))

(defn can-recomendation-friend? [user]
  (get user :enable_friends_recommendation))

(defn recommendation [data id]
  (let [friends (get-friends data id)
        find (get friends id)
        recommendation (map #(get-friends data %) find)
        clear-recommendations (reduce into (remove nil? recommendation))]
    (-> (vals clear-recommendations)
        (flatten)
        (set)
        (set/difference (set (get friends id)))
        (disj id))))
