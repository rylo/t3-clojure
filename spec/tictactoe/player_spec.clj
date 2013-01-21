(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]
			[tictactoe.game_rules :refer :all]
			[tictactoe.board :refer :all]))

(describe "Player"
	(def human-player (tictactoe.player.Human. "x"))
	(def easy-computer-player (tictactoe.player.EasyComputer. "x"))
	(def ultimate-computer-player (tictactoe.player.UltimateComputer. "x"))
	
	(defn contains-value? [value collection]
		(not (nil? (some #{value} collection))))
	
	(it "has a marker"
		(should= "x" (:marker human-player)))

	(context "Human Player"
		(context "get-move"
			(it "should return the user's move if it is valid"
				(let [board (make-board 9 nil)]
					(should= 1 (with-redefs [read-line (constantly "1") println (constantly "")] (get-move human-player board)))))
			(it "should prompt the user for another move if it is invalid" (pending "ability to test recursive input"))))
	
	(context "Easy Computer Player (random spots)"
		(context "get-move"
			(it "should return a move in an empty space"
				(let [board ["x" "x" "x" "x" "x" "x" "x" "x" nil]] (should= 8 (get-move easy-computer-player board))))))

	(context "Ulimate Computer Player (minimax algorithm)"
		(context "get-score"
			(it "should return a number score based on the state of the board"
				(let [board ["x" "x" "x" "x" "x" "x" "x" "x" "x"]] (should= 100 (get-score "x" board)))
				(let [board ["x" "x" "x" "x" "x" "x" "x" "x" "x"]] (should= -100 (get-score "o" board)))
				(let [board ["o" "o" "o" "o" "o" "o" "o" "o" "o"]] (should= -100 (get-score "x" board)))
				(let [board ["x" nil nil nil "o" nil nil nil "o"]] (should= 0 (get-score "o" board)))
				(let [board ["x" "x" "x" nil "o" nil nil nil "o"]] (should= -100 (get-score "o" board)))))
		
		(context "get-move"
			(it "should return a move in an empty space"
				(let [board ["x" "x" "x" "x" "x" "x" "x" "x" nil]] (should= 8 (get-move ultimate-computer-player board)))
				(let [board ["o" "o" "x" "x" "o" "x" nil nil "o"]] (should (contains-value? (get-move ultimate-computer-player board) [6 7])))))
	)
)

(run-specs)