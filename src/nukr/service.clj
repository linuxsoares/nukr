(ns nukr.service
  (:require [io.pedestal.http :as bootstrap]
            [io.pedestal.interceptor.chain :refer [terminate]]
            [io.pedestal.interceptor :refer [interceptor]]
            [pedestal-api
             [core :as api]
             [helpers :refer [before defbefore defhandler handler]]]
            [schema.core :as s]
            [nukr.api.profile :as profile]
            [nukr.api.friendship :as friendship]
            [nukr.api.recommendation :as recommendation]))

(def no-csp
  {:name ::no-csp
   :leave (fn [ctx]
            (assoc-in ctx [:response :headers "Content-Security-Policy"] ""))})

(s/with-fn-validation
  (api/defroutes routes
    {:info {:title       "Swagger documentation to create a user profile and add friendship"
            :description "Find out more at https://github.com/linuxsoares/nukr/blob/master/README.md"
            :version     "1.0"}
     :tags [{:name         "Users"
             :description  "Everything about Users"
             :externalDocs {:description "Find out more"
                            :url         "http://swagger.io"}}]}
    [[["/" ^:interceptors [api/error-responses
                           (api/negotiate-response)
                           (api/body-params)
                           api/common-body
                           (api/coerce-request)
                           (api/validate-response)]
       ["/users" ^:interceptors [(api/doc {:tags ["users"]})]
        ["/" {:get profile/all-users
              :post profile/create-user}]
        ["/:id" ^:interceptors [profile/load-user]
         {:get profile/get-user
          :put profile/update-user
          :delete profile/delete-user}]
        ["/:id/friendships" ^:interceptors
         {:get friendship/friendships
          :post friendship/add-friend
          :delete friendship/remove-friend}]
        ["/:id/recommendations" ^:interceptors
         {:get recommendation/recommendations}]]

       ["/swagger.json" {:get api/swagger-json}]
       ["/*resource" ^:interceptors [no-csp] {:get api/swagger-ui}]]]]))

(def service
  {:env                      :prod
   ::bootstrap/routes        #(deref #'routes)
   ;; linear-search, and declaring the swagger-ui handler last in the routes,
   ;; is important to avoid the splat param for the UI matching API routes
   ::bootstrap/router        :linear-search
   ::bootstrap/resource-path "/public"
   ::bootstrap/type          :jetty
   ::bootstrap/port          8080
   ::bootstrap/join?         false})
