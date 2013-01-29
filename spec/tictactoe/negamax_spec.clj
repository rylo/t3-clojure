(ns tictactoe.negamax-spec
  (:require [speclj.core :refer :all]
			[tictactoe.negamax :refer :all]
			[tictactoe.game_rules :refer :all]))

(context "UltimateComputer negamax"
	(context "score-empty-spaces"
		(it "should return an array of best moves"
			(should= [50 100 50 50 100] (score-empty-spaces "x" ["x" "o" nil nil nil nil "x" "x" nil]))
			(should= [50 50 50 100] (score-empty-spaces "o" ["x" nil "x" nil "x" nil "o" nil "o"]))
		)
	)
			
	(context "get-best-move"
		(it "should return the space index of the optimal move"
			(should= 8 (get-best-move "x" ["x" "o" nil nil nil nil "x" "x" nil]))
			(should= 3 (get-best-move "x" ["x" "o" nil nil nil nil "x" nil nil]))
			(should= 2 (get-best-move "o" ["x" "x" nil nil nil nil "o" nil nil]))
			(should= 6 (get-best-move "o" [nil nil "x" nil "x" nil nil nil "o"]))
			(should= 7 (get-best-move "o" ["x" nil "x" nil "x" nil "o" nil "o"]))
			(should= 4 (get-best-move "o" ["x" nil nil nil nil nil nil nil nil]))
			))

	(context "get-score"
		(it "should return a number score based on the state of the board"
			(should= true (game-over-with-tie? ["x" "o" "x" "o" "o" "x" "x" "x" "o"]))
			(should= 100 (get-score "x" ["x" "x" "x" "x" "x" "x" "x" "x" "x"]))
			(should= -100 (get-score "o" ["x" "x" "x" "x" "x" "x" "x" "x" "x"]))
			(should= -100 (get-score "x" ["o" "o" "o" "o" "o" "o" "o" "o" "o"]))
			(should= 0 (get-score "x" ["x" "o" "x" "o" "o" "x" "x" "x" "o"])))))
				
(run-specs)