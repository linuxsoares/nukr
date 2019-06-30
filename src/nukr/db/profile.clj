(ns nukr.db.profile)

(defonce profiles (atom {}))

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
  (some #(= (:email %) email) (vals @profiles)))

(defn update-user [id user]
  (swap! profiles update id merge user))

(defn delete-user [id]
  (swap! profiles dissoc id))
