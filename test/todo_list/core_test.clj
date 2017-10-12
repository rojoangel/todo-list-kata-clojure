(ns todo-list.core-test
  (:require [clojure.test :refer :all]
            [todo-list.core :refer :all]))

(defn item
  ([description] (item description false))
  ([description checked] {:description description :checked checked}))

(defn todo-list [& items]
  (reduce conj [] items))

(defn add [list description]
  (cons (item description) list))

(defn check-item [item]
  (conj item {:checked true}))

(defn uncheck-item [item]
  (conj item {:checked false}))

(defn check [list item-to-check]
  (map
    (fn [item]
      (if (= (:description item) item-to-check)
        (check-item item)
        item))
    list))

(defn uncheck [list item-to-uncheck]
  (map
    (fn [item]
      (if (= (:description item) item-to-uncheck)
        (uncheck-item item)
        item))
    list))

(deftest todo-list-test
  (let [aDescription "buy milk" anotherDescription "buy sugar"]
    (testing "An item can be added"
      (is (= (add (todo-list (item aDescription)) anotherDescription)
             (todo-list (item anotherDescription) (item aDescription)))))
    (testing "An item can be checked"
      (is (= (check (todo-list (item aDescription) (item anotherDescription)) aDescription)
             (todo-list (item aDescription true) (item anotherDescription)))))
    (testing "An item can be unchecked"
      (is (= (uncheck (todo-list (item aDescription true) (item anotherDescription)) aDescription)
             (todo-list (item aDescription) (item anotherDescription)))))))
