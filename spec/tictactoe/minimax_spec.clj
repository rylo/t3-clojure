(ns tictactoe.minimax-spec
  (:require [speclj.core :refer :all]
			[tictactoe.minimax :refer :all]))

(context "UltimateComputer minimax"
	(context "get-best-moves"
		(it "should return an array of best moves"
			(pending "finishing minimax algorithm")
			(let [board ["x" "x" "x" "x" "x" "x" "x" "x" nil]] (should= [8] (get-best-moves "x" board)))
			(let [board ["x" "x" "x" "x" "x" "x" "x" nil nil]] (should= [7 8] (get-best-moves "x" board)))
			(let [board ["x" "x" nil nil nil nil nil nil nil]] (should= [2] (get-best-moves "x" board)))
			(let [board [nil nil nil nil nil nil nil nil nil]] (should= [5] (get-best-moves "x" board)))))

	(context "end-of-tree?"
		(it "should return false if the game is not over"
			(let [board ["x" "x" "x" "x" "x" "x" "x" "x" nil]] (should= true (end-of-tree? board)))
			(let [board [nil nil nil nil nil nil nil nil nil]] (should= false (end-of-tree? board)))))

	(context "get-score"
		(it "should return a number score based on the state of the board"
			(let [board ["x" "x" "x" "x" "x" "x" "x" "x" "x"]] (should= 100 (get-score "x" board)))
			(let [board ["x" "x" "x" "x" "x" "x" "x" "x" "x"]] (should= -100 (get-score "o" board)))
			(let [board ["o" "o" "o" "o" "o" "o" "o" "o" "o"]] (should= -100 (get-score "x" board)))
			(let [board ["x" nil nil nil "o" nil nil nil "o"]] (should= 0 (get-score "o" board)))
			(let [board ["x" "x" "x" nil "o" nil nil nil "o"]] (should= -100 (get-score "o" board))))))
			
(run-specs)