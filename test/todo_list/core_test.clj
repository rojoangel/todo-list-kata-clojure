(ns todo-list.core-test
  (:require [clojure.test :refer :all]
            [todo-list.core :refer :all]))

(defn item
  ([description] (item description false))
  ([description checked] {:description description :checked checked}))

(defn add [list description]
  (cons (item description) list))

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
      (is (= (add [(item aDescription)] anotherDescription)
             [(item anotherDescription) (item aDescription)])))
    (testing "An item can be checked"
      (is (= (check [(item aDescription)] aDescription)
             [(item aDescription true)]))
      (is (= (check [(item aDescription) (item anotherDescription)] aDescription)
             [(item aDescription true) (item anotherDescription)])))))
