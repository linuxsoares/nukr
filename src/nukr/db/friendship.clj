(ns nukr.db.friendship)

(defonce friendships (atom {}))

(defn get-friendships-by-use-id [id]
  (get @friendships id))

(defn add-friend [user-id friend-id]
  (let [user (get @friendships user-id)
        friends (get-in user [user-id])]
    (swap! friendships assoc user-id (assoc {user-id (conj friends friend-id)} :id user-id))))

(defn remove-friend [user-id friend-id]
  (let [friends (get-in @friendships [user-id user-id])]
    (swap! friendships assoc user-id
           (assoc {user-id (remove #{friend-id} friends)} :id user-id))))