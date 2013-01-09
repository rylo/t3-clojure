(ns tictactoe.io)

(defn get-input [] (read-line))

(defn set-output [output-str] (println output-str))

(defn prompt [question] (set-output question) (get-input))