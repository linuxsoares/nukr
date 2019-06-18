(ns nukr.http.schema
  (:require [schema.core :as s]))

(s/defschema User
  {:about s/Str
   :address {:city s/Str
             :country s/Str
             :region s/Str
             :state s/Str
             :street s/Str
             :zipcode s/Str}
   ;:birthday s/Inst
   :email s/Str
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
   ;:birthday s/Inst
   :email s/Str
   :first_name s/Str
   :last_name s/Str
   :gender s/Str
   :languages [s/Str]
   :name s/Str
   :short_name s/Str
   :enable_friends_recommendation s/Bool})
