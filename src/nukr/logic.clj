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

(defn can-recomendation-friend? [user]
  (get user :enable_friends_recommendation))

(defn get-friends [data id]
  (let [user (get data id)]
    (get user id)))

(defn remove-user-recommendation-disabled [users]
  (let [enabled-users (filter #(can-recomendation-friend? %) users)]
    enabled-users))

(defn clean-recommendations [data recommendations user-id]
  (let [friends (get-friends data user-id)]
    (set (remove (set friends) recommendations))))

(defn ^:private find-recommendations [data user-id users users-recommendations researched]
  (let [recommendation (remove nil? (flatten (map #(get-friends data %) users)))
        find-more-users (remove #{user-id} recommendation)
        find-users (remove (set researched) find-more-users)
        user-recommendations (concat users-recommendations find-more-users)
        user-researched (concat users researched)]
    (if (not-empty find-more-users)
      (recur data user-id find-users user-recommendations user-researched)
      (clean-recommendations data users-recommendations user-id))))

(defn recommendations
  ([data user-id]
   (let [friends (get-friends data user-id)]
     (if (not-empty friends)
       (find-recommendations data user-id friends nil nil)))))
