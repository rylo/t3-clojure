(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "Board"
	(it "contain 5 spaces"
		(let [board [" ", " ", " ", " ", " "]]
			(should (= (board-size board) 5))))
			
	(it "contains 9 elements"
		(let [board (take 9 (repeat " "))]
			(should (= (board-size board) 9))))
		
	(it "creates a board with 9 blank spaces"
		(let [board (make-board 9 " ")]
			(should= 9 (board-size board))
			(dotimes [n 9] (should= " " (get-marker board n)))))
	
	(it "returns the mark at a given space"
		(let [board (make-board 9 "0")]
			(should= (get-marker board 8) "0")))
			
	(context "set-marker"
		(it "returns a board with a changed marker at the index given"
			(let [board (make-board 9 "0") board2 (set-marker board "x" 5)]
				(should= ["0" "0" "0" "0" "0" "0" "0" "0" "x"] (set-marker board "x" 8))
				(should= ["0" "0" "0" "x" "0" "0" "0" "0" "0"] (set-marker board "x" 3))
				(should= ["0" "0" "0" "0" "x" "0" "0" "0" "0"] (set-marker board "x" 4))
				(should= ["0" "x" "0" "0" "0" "0" "0" "0" "0"] (set-marker board "x" 1)))))
	
	(context "get-rows"
		(it "returns the board's 'horizontal' rows" 
			(let [board (make-board 9 "0") row-guide [[0 1 2] [3 4 5] [6 7 8]]]
				(should= [["0" "0" "x"] ["0" "0" "0"] ["0" "0" "0"]] (get-rows (set-marker board "x" 2) row-guide))
				(should= [["0" "0" "0"] ["0" "x" "0"] ["0" "0" "0"]] (get-rows (set-marker board "x" 4) row-guide))))
	
		(it "returns a single row from the board" 
			(let [board (make-board 9 "0") row-guide [[0 1 2]]]
				(should= (get-rows (set-marker board "x" 2) row-guide) [["0" "0" "x"]])))
	
		(it "returns the board's 'vertical' rows" 
			(let [board (make-board 9 "0") row-guide [[0 3 6] [1 4 7] [2 5 8]]]
				(should= [["0" "0" "0"] ["0" "0" "0"] ["x" "0" "0"]] (get-rows (set-marker board "x" 2) row-guide))
				(should= [["0" "x" "0"] ["0" "0" "0"] ["0" "0" "0"]] (get-rows (set-marker board "x" 3) row-guide)))))
	
	(it "returns the spaces at the locations in a vector"
		(let [board (set-marker (make-board 9 "0") "x" 1)]
			(should= ["0" "x" "0" "0" "0" "0" "0" "0" "0"] board)
			(should= ["0" "0" "0"] (get-markers board [0 3 6]))
			(should= ["0" "x" "0"] (get-markers (set-marker board "x" 3) [0 3 6]))))
	
	(it "returns the board's diagonal rows" 
		(let [board (make-board 9 "0") row-guide [[0 4 8] [2 4 6]]] 
			(should= [["0" "x" "0"] ["0" "x" "0"]] (get-rows (set-marker board "x" 4) row-guide))))

	(context "row-taken?"
		(it "returns false if the row doesn't contain 3 of the same string" 
			(let [board (make-board 9 nil) row [0 1 2]]
				(should= false (row-taken? board row))))
				
		(it "returns true if the row contains 3 of the same string" 
			(let [board (make-board 9 "x") row [0 1 2]]
				(should= true (row-taken? board row)))))

	(context "row-taken-by?"
		(it "returns false if the row doesn't contain 3 of the same string" 
			(let [board (make-board 9 nil) row (get-rows board [[1 2 3]])]
				(should= false (row-taken-by? "x" board row))))

		(it "returns true if the row contains 3 of the same string" 
			(let [board (make-board 9 "x") row (get-rows board [[1 2 3]])]
				(should= true (row-taken-by? "x" board row)))))
				
	(context "generate-winning-combinations"
		(it "returns a vector of row vectors"
			(let [board (make-board 9 nil) 
				  full-row-vector [[0 1 2][3 4 5][6 7 8][0 3 6][1 4 7][2 5 8][0 4 8][2 4 6]]]
				(should= full-row-vector (generate-winning-combinations)))))
			
	(context "full?"
		(it "should return true if the board has 9 moves on it"
			(let [board (make-board 9 "x")]
				(should= true (full? board))))
				
		(it "should return false if the board has less than 9 moves on it"
			(let [board (make-board 9 nil)]
				(should= false (full? board))))))

(run-specs)