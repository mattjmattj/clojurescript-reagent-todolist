(ns todolist.core
  (:require [reagent.core :as r]
            [todolist.components :as components]
            [todolist.store :as store]))

(enable-console-print!)

; (store/add-todo! "coucou")

(defn main []
  [:div
    [:h1 "Todo list app"]
    [components/todo-list]
  ])


(r/render [main]
  (js/document.getElementById "app"))