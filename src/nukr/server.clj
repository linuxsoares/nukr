(ns nukr.server
  (:require [nukr.service :as service]
            [io.pedestal.http :as bootstrap])
  (:gen-class))

(defonce service-instance nil)

(defn create-server []
  (alter-var-root #'service-instance
                  (constantly (bootstrap/create-server
                               (-> service/service
                                   (assoc ::bootstrap/port (Integer. (or (System/getenv "PORT") "5000")))
                                   (assoc ::bootstrap/host (or (System/getenv "HOST") "localhost"))
                                   (bootstrap/default-interceptors))))))

(defn start []
  (when-not service-instance
    (create-server))
  (bootstrap/start service-instance))

(defn stop []
  (when service-instance
    (bootstrap/stop service-instance)))

(defn -main [& args]
  (start))
