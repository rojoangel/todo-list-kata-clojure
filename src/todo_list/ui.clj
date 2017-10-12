(ns todo-list.ui
  (:gen-class)
  (:require [clojure.string :as str]
            [todo-list.core]))

(declare ui-loop)

(defn- dispatch [command]
  (case command

    :quit
    (System/exit 0)

    ;;otherwise
    (println "try quit"))

  (ui-loop))

(defn- ui-loop []
  (let [[command-str & _] (str/split (read-line) #" ")
        command (keyword command-str)]
    (dispatch command)))

(defn -main [& args]
  (ui-loop))