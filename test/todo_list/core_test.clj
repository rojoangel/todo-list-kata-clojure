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

(defn- check-item [item]
  (conj item {:checked true}))

(defn- uncheck-item [item]
  (conj item {:checked false}))

(defn- update-matches [list f item-desc]
  (map
    (fn [item]
      (if (= (:description item) item-desc)
        (f item)
        item))
    list))

(defn check [list item-desc]
  (update-matches list check-item item-desc))

(defn uncheck [list item-desc]
  (update-matches list uncheck-item item-desc))

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
