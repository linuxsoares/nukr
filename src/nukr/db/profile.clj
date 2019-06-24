(ns nukr.db.profile)

(defonce profiles (atom {}))
(defonce friendships (atom {}))

(defn create-user [user]
  (let [id (inc (count @profiles))]
    (get (swap! profiles assoc id (assoc user :id id)) id)))

(defn all-profile []
  (cond->> (vals @profiles)
    sort (sort-by :id)
    (= :desc sort) reverse))

(defn get-user-by-id [id]
  (get @profiles id))

(defn get-user-by-email [email]
  (filter #(= (:email %) email) (vals @profiles)))

(defn update-user [id user]
  (swap! profiles update id merge user))

(defn delete-user [id]
  (swap! profiles dissoc id))

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
