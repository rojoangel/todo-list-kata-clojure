(ns todo-list.core-test
  (:require [clojure.test :refer :all]
            [todo-list.core :refer :all]))

(deftest todo-list-test
  (let [aDescription "buy milk" anotherDescription "buy sugar"]
    (testing "An item can be added"
      (is (= (add-item (todo-list (item aDescription)) anotherDescription)
             (todo-list (item anotherDescription) (item aDescription)))))
    (testing "An item can be checked"
      (is (= (check (todo-list (item aDescription) (item anotherDescription)) aDescription)
             (todo-list (item aDescription true) (item anotherDescription)))))
    (testing "An item can be unchecked"
      (is (= (uncheck (todo-list (item aDescription true) (item anotherDescription)) aDescription)
             (todo-list (item aDescription) (item anotherDescription)))))
    (testing "An item can be removed"
      (is (= (remove-item (todo-list (item aDescription true) (item anotherDescription)) aDescription)
             (todo-list (item anotherDescription)))))))
