(ns nukr.process
  (:require [clojure.set :as s]))

(defn get-friends [data id]
  (get data id))

(defn recomendation-friends [data id]
  (let [friends (get-friends data id)
        recomendation (map #(get-friends data %) friends)
        recomendation-finish (reduce into (remove nil? recomendation))]
    (disj (s/difference (set recomendation-finish) (set friends)) id)))
