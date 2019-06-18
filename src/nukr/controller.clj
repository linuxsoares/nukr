(ns nukr.controller
  (:require [nukr.db.profile :as profile]))

(defn create-pet [pet]
  (profile/create-pet pet))

(defn create-user [user]
  (profile/create-user user))

(defn all-pets []
  (profile/all-pets))

(defn all-users []
  (profile/all-profile))

(defn get-user-by-id [id]
  (profile/get-user-by-id id))

(defn update-user [id user]
  (profile/update-user id user))

(defn delete-user [id]
  (profile/delete-user id))