(ns todo-list.core-test
  (:require [clojure.test :refer :all]
            [todo-list.core :refer :all]))

(defn add [list name]
  (cons name list))

(defn check-item [item]
  (conj item {:checked true}))

(defn check [list item-to-check]
  (map
    (fn [item]
      (if (= (:description item) item-to-check)
        (check-item item)
        item))
    list))

(deftest todo-list
  (let [aDescription "buy milk" anotherDescription "buy sugar"]
    (testing "An item can be added"
      (is (= (add [aDescription] anotherDescription)
             [anotherDescription aDescription])))
    (testing "An item can be checked"
      (is (= (check [{:description aDescription}] aDescription)
             [{:description aDescription :checked true}]))
      (is (= (check [{:description aDescription} {:description anotherDescription}] aDescription)
             [{:description aDescription :checked true} {:description anotherDescription}])))))