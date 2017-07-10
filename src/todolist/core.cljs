(ns todolist.core
  (:require [reagent.core :as r]
            [todolist.components :refer [todo-list]]
            [todolist.store :as store]))

(enable-console-print!)

; (store/add-todo! "coucou")

(defn main []
  [:div
    [:h1 "Todo list app"]
    (todo-list {
      :todos @store/todos
      :on-new-todo store/add-todo!
      :on-toggle-todo store/toggle-todo!
    })
  ])


(r/render [main]
  (js/document.getElementById "app"))