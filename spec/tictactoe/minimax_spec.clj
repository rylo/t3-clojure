(ns tictactoe.minimax-spec
  (:require [speclj.core :refer :all]
			[tictactoe.minimax :refer :all]))

(context "UltimateComputer minimax"
	(context "score-empty-spots"
		(it "should return an array of best moves"
			(should= [-100 100 -100 0 100] (score-empty-spots "x" ["x" "o" nil nil nil nil "x" "x" nil]))))
			
	(context "get-best-move"
		(it "should return the value"
			(should= 8 (get-best-move "x" ["x" "o" nil nil nil nil "x" "x" nil]))))

	(context "get-score"
		(it "should return a number score based on the state of the board"
			(let [board ["x" "x" "x" "x" "x" "x" "x" "x" "x"]] (should= 100 (get-score "x" board)))
			(let [board ["x" "x" "x" "x" "x" "x" "x" "x" "x"]] (should= -100 (get-score "o" board)))
			(let [board ["o" "o" "o" "o" "o" "o" "o" "o" "o"]] (should= -100 (get-score "x" board)))
			(let [board ["x" nil nil nil "o" nil nil nil "o"]] (should= 0 (get-score "o" board)))
			(let [board ["x" "x" "x" nil "o" nil nil nil "o"]] (should= -100 (get-score "o" board))))))
			
	(context "minimax"
		(it "should return a number score based on the state of the board"
            (should= 100 (minimax ["x" "x" "x" nil nil nil nil nil nil] "x" 1))
			(should= -100 (minimax ["o" "o" "o" nil nil nil nil nil nil] "x" 1))
		    (should= 0 (minimax ["x" "o" "x" "o" "o" "x" nil "x" "o"] "x" 1))))
				
(run-specs)