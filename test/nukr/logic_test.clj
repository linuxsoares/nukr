(ns nukr.logic-test
  (:require [clojure.test :refer :all]
            [nukr.logic :as logic]))

(deftest test-logic-build-friendships
  (let [friends [{:name "friend",
                  :id "11"
                  :enable_friends_recommendation true}
                 {:name "friend1",
                  :id "12"
                  :enable_friends_recommendation true}]
        user {:name "user",
              :id "1"}
        expected {:user-id "1" :name "user", :friends '({:id "11", :name "friend", :can-recommendation true} {:id "12", :name "friend1", :can-recommendation true})}
        response (logic/format-friendships user friends)]
    (is (=
         expected response))))

(deftest test-logic-build-recommendations
  (let [friends [{:name "friend",
                  :id "11"
                  :enable_friends_recommendation true}
                 {:name "friend1",
                  :id "12"
                  :enable_friends_recommendation true}]
        user {:name "user",
              :id "1"}
        expected {:user-id "1" :name "user", :recommendations '({:id "11", :name "friend", :can-recommendation true} {:id "12", :name "friend1", :can-recommendation true})}
        response (logic/format-recommendations user friends)]
    (is (=
         expected response))))

(deftest test-should-recommendation-friends
  (let [data {1 {1 '(2), :id 1}, 2 {2 '(1 3), :id 2}}
        friends-recommendation (logic/recommendations data 1)]
    (is (= #{3} friends-recommendation))))

(deftest test-should-recommendation-friends-tree
  (let [data {1 {1 '(2), :id 1}, 2 {2 '(1 3), :id 2}, 3 {3 '(1 2 4 5), :id 3}}
        friends-recommendation (logic/recommendations data 1)]
    (is (= #{3 4 5} friends-recommendation))))
