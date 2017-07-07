(ns todolist.store
  (:require [reagent.core :as r]))


(defonce todos (r/atom (sorted-map)))

(defonce todosId (r/atom 0))

(defn add-todo! [text]
  (
    let [newid (swap! todosId inc)]
    (swap! todos assoc newid {:id newid :text text :done false})
  )
)

(defn toggle-todo! [id]
  (swap! todos update-in [id :done] not)
)

(defn remove-todo! [id]
  (swap! todos dissoc id)
)