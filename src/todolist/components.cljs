(ns todolist.components
  (:require [reagent.core :as r]))

(defonce -new-todo-value (r/atom nil))
(defonce -new-todo-id "new-todo")

(defn new-todo-form [on-new-todo]
  [:form {
      :action ""
      :on-submit #(
        (on-new-todo @-new-todo-value)
        (reset! -new-todo-value nil)
        (.preventDefault %))}

    [:label {:for -new-todo-id} "Add something"]
    [:input {
      :name -new-todo-id
      :type "text"
      :required "required"
      :value @-new-todo-value
      :on-change #(reset! -new-todo-value (-> % .-target .-value))}]

    [:input {:type "submit" :value "Ajouter"}]
  ]
)

(defn todo-item [{:keys [id text done]} on-toggle]
  (
    let [checkbox-id (str "todo-item" id)]
    [:li {:key id} 
      [:label {:for checkbox-id } 
        text
      ]
      [:input {
        :type "checkbox"
        :checked done
        :on-change #(on-toggle id)
        }
      ]
    ] 
  )
)

(defn todo-list [{:keys [todos on-toggle-todo on-new-todo]}]
  (
    let [todos-by-done (group-by :done (vals todos))]
    [:div
      [:h2 "List of stuff to do"]
      [:ul
        (map #(todo-item % on-toggle-todo) (get todos-by-done false))
      ]
      (new-todo-form on-new-todo)
      [:h2 "List of stuff done"]
      [:ul
        (map #(todo-item % on-toggle-todo) (get todos-by-done true))
      ]
    ]
  )
) 