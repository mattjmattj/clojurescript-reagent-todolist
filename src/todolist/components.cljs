(ns todolist.components
  (:require [reagent.core :as r]
            [todolist.store :as store]))

(defn -todo-item [todo]
  (
    let [{:keys [id text done]} todo
         checkbox-id (str "todo-item" id)]
    [:li {:key id} 
      [:label {:for checkbox-id } 
        text
      ]
      [:input {
        :type "checkbox"
        :checked done
        :on-change #(store/toggle-todo! id)
        }
      ]
    ] 
  )
)

(defonce -new-todo-value (r/atom nil))
(defonce -new-todo-id "new-todo")

(defn -new-todo-form []
  [:form {
      :action ""
      :on-submit #(
        (store/add-todo! @-new-todo-value)
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

(defn todo-list []
  (
    let [todos-by-done (group-by :done (vals @store/todos))]
    [:div
      [:h2 "List of stuff to do"]
      [:ul
        (map -todo-item (get todos-by-done false))
      ]
      (-new-todo-form)
      [:h2 "List of stuff done"]
      [:ul
        (map -todo-item (get todos-by-done true))
      ]
    ]
  )
) 