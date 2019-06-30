(ns nukr.api.schema
  (:require [schema.core :as s]))

(s/defschema User
  {:about s/Str
   :address {:city s/Str
             :country s/Str
             :region s/Str
             :state s/Str
             :street s/Str
             :zipcode s/Str}
   :birthday #"\d{4}-\d{2}-\d{2}"
   :email #"^[\w+.]+@\w+\.\w{2,}(?:\.\w{2})?$"
   :first_name s/Str
   :last_name s/Str
   :gender s/Str
   :languages [s/Str]
   :name s/Str
   :short_name s/Str
   :enable_friends_recommendation s/Bool})

(s/defschema UserResp
  {:id s/Int
   :about s/Str
   :address {:city s/Str
             :country s/Str
             :region s/Str
             :state s/Str
             :street s/Str
             :zipcode s/Str}
   :birthday #"\d{4}-\d{2}-\d{2}"
   :email #"^[\w+.]+@\w+\.\w{2,}(?:\.\w{2})?$"
   :first_name s/Str
   :last_name s/Str
   :gender s/Str
   :languages [s/Str]
   :name s/Str
   :short_name s/Str
   :enable_friends_recommendation s/Bool})

(s/defschema FriendResp
  {:id s/Int
   :name s/Str
   :can-recommendation s/Bool})

(s/defschema FriendShipResp
  {:user-id s/Int
   :name s/Str
   :friends [FriendResp]})

(s/defschema Friend
  {:friend {:id s/Int}})

(s/defschema RecommendationResp
  {:user-id s/Int
   :name s/Str
   :recommendations [FriendResp]})
