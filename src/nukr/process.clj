(ns nukr.process)

(defn get-friends [data name]
    (get data name))

(defn recomendation-friends
    ([data name] (recomendation-friends data name {}))
    ([data name friends-recomendation]
        (let [friends (get-friends data name)
              recomendation (map #(get-friends data %) friends)]
              (remove nil? recomendation))))