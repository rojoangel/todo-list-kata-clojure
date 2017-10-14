(ns todo-list.ui
  (:gen-class)
  (:require [clojure.string :as str]
            [todo-list.core :as todo]))

(declare ui-loop)

(defn- dispatch [todo-list command item-desc]
  (->
    (case command

      :quit
      (System/exit 0)

      :add
      (todo/add-item todo-list item-desc)

      ;;otherwise
      (do
        (println "try quit")
        todo-list))

    (ui-loop)))

(defn- ui-loop [todo-list]
  (let [[command-str item-desc & _] (str/split (read-line) #" ")
        command (keyword command-str)]
    (dispatch todo-list command item-desc)))

(defn -main [& args]
  (ui-loop (todo/todo-list)))
