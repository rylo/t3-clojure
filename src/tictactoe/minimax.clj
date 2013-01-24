(ns tictactoe.minimax
	(:require [tictactoe.game_rules :refer :all]
			  [tictactoe.board :refer :all]
			  [tictactoe.io :refer :all]))

(defn alternate-player-marker [player-marker]
	(if (= "x" player-marker)
		"o"
		"x"))

(defn get-score [player-marker board]
	(if (game-over-with-tie? board)
		0
		(if (= player-marker (get-winner board))
			 100
			-100)))

(defn minimax [board player-marker depth]
	(let [space (rand-nth (empty-spaces board))
	      altered-board (set-marker board player-marker space)]
		(if (game-over? altered-board)
			(do (print (printable-board altered-board)) 
				(println (/ (get-score player-marker altered-board) depth)) 
				(/ (get-score player-marker altered-board) depth))
			(* -1 (minimax altered-board (alternate-player-marker player-marker) (inc depth))))))

(defn score-empty-spaces [player-marker board]
	(for [empty-space (empty-spaces board)
		  :let [altered-board (set-marker board player-marker empty-space)]]
			; (do (println empty-space) (print (printable-board altered-board) )
					(* -1 (minimax altered-board (alternate-player-marker player-marker) 1))))
		
(defn get-best-move [player-marker board] 
	(interleave (empty-spaces board) (score-empty-spaces player-marker board)))
	; (key (apply max-key val (apply hash-map (interleave (empty-spaces board) (score-empty-spaces player-marker board))))))
	
	
	; (println player-marker)
	; (print (printable-board board))
	; (println (get-score player-marker board))
	
	
	; open spaces
	;  open spaces