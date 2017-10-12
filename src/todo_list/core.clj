(ns todo-list.core)

(defn item
  ([description] (item description false))
  ([description checked] {:description description :checked checked}))

(defn todo-list [& items]
  (reduce conj [] items))

(defn- check-item [item]
  (conj item {:checked true}))

(defn- uncheck-item [item]
  (conj item {:checked false}))

(defn- has-desc? [item desc]
  (= (:description item) desc))

(defn- update-matches [list f item-desc]
  (map
    (fn [item]
      (if (has-desc? item item-desc)
        (f item)
        item))
    list))

(defn add-item [list description]
  (cons (item description) list))

(defn remove-item [list item-desc]
  (remove #(has-desc? % item-desc) list))

(defn check [list item-desc]
  (update-matches list check-item item-desc))

(defn uncheck [list item-desc]
  (update-matches list uncheck-item item-desc))
