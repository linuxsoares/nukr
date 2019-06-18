(ns nukr.db.profile)

(defonce profiles (atom {}))
(defonce the-pets (atom {}))

(defn create-pet [pet]
  (let [id (inc (count @the-pets))]
    (get (swap! the-pets assoc id (assoc pet :id id)) id)))

(defn create-user [user]
  (let [id (inc (count @profiles))]
    (get (swap! profiles assoc id (assoc user :id id)) id)))

(defn all-pets []
  (cond->> (vals @the-pets)
           sort (sort-by :id)
           (= :desc sort) reverse))

(defn create-profile [profile]
  (let [id (inc (count @profiles))]
    (get (swap! profiles assoc id (assoc profile :id id)) id)))

(defn all-profile []
  (cond->> (vals @profiles)
           sort (sort-by :id)
           (= :desc sort) reverse))

(defn get-user-by-id [id]
  (get @profiles id))

(defn update-user [id user]
  (swap! profiles update id merge user))

(defn delete-user [id]
  (swap! profiles dissoc id))