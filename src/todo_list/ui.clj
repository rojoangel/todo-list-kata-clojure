(ns todo-list.ui
  (:gen-class)
  (:require [clojure.string :as str]
            [todo-list.core :as todo]))

(defn show-item [item]
  (println
    (if (:checked item)
      "[x]"
      "[ ]")
    (:description item)))

(defn show-list [list]
  (doseq [item list]
    (show-item item)))

(declare ui-loop)

(defn- dispatch [todo-list command item-desc]
  (->
    (case command

      :quit
      (System/exit 0)

      :add
      (todo/add-item todo-list item-desc)

      :remove
      (todo/remove-item todo-list item-desc)

      :check
      (todo/check todo-list item-desc)

      ;;otherwise
      (do
        (println "try quit")
        todo-list))

    (ui-loop)))

(defn- ui-loop [todo-list]
  (do
    (show-list todo-list)
    (let [[command-str item-desc] (str/split (read-line) #" " 2)
        command (keyword command-str)]
      (dispatch todo-list command item-desc))))

(defn -main [& args]
  (ui-loop (todo/todo-list)))
