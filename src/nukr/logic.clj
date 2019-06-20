(ns nukr.logic)

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
