(ns tictactoe.core-spec
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
			
	(it "returns a board with a changed marker at the index given"
		(let [board (make-board 9 "0") board2 (set-marker board "x" 5)]
			(should= (set-marker board "x" 8) ["0" "0" "0" "0" "0" "0" "0" "x" "0"])
			(should= (set-marker board "x" 3) ["0" "0" "x" "0" "0" "0" "0" "0" "0"])
			(should= (set-marker board "x" 4) ["0" "0" "0" "x" "0" "0" "0" "0" "0"])
			(should= (set-marker board "x" 1) ["x" "0" "0" "0" "0" "0" "0" "0" "0"])
			(should= (set-marker board "x" 9) ["0" "0" "0" "0" "0" "0" "0" "0" "x"])))
	
	(context "get-rows"
		(it "returns the board's 'horizontal' rows" 
			(let [board (make-board 9 "0") row-guide [[1 2 3] [4 5 6] [7 8 9]]]
				(should= (get-rows (set-marker board "x" 2) row-guide) [["0" "x" "0"] ["0" "0" "0"] ["0" "0" "0"]])
				(should= (get-rows (set-marker board "x" 4) row-guide) [["0" "0" "0"] ["x" "0" "0"] ["0" "0" "0"]])))
	
		(it "returns a single row from the board" 
			(let [board (make-board 9 "0") row-guide [[1 2 3]]]
				(should= (get-rows (set-marker board "x" 3) row-guide) [["0" "0" "x"]])))
	
		(it "returns the board's 'vertical' rows" 
			(let [board (make-board 9 "0") row-guide [[1 4 7] [2 5 8] [3 6 9]]]
				(should= (get-rows (set-marker board "x" 2) row-guide) [["0" "0" "0"] ["x" "0" "0"] ["0" "0" "0"]])
				(should= (get-rows (set-marker board "x" 3) row-guide) [["0" "0" "0"] ["0" "0" "0"] ["x" "0" "0"]]))))
	
	(it "returns the spaces at the locations in a vector"
		(let [board (set-marker (make-board 9 "0") "x" 1)]
			(should= ["x" "0" "0" "0" "0" "0" "0" "0" "0"] board)
			(should= ["x" "0" "0"] (get-markers board [1 4 7]))
			(should= ["0" "x" "0"] (get-markers (set-marker board "x" 4) [1 4 7]))))
	
	(it "returns the board's diagonal rows" 
		(let [board (make-board 9 "0") row-guide [[1 5 9] [3 5 7]]] 
			(should= [["0" "x" "0"] ["0" "x" "0"]] (get-rows (set-marker board "x" 5) row-guide))))
			
	(it "returns false if the a spot is nil" 
		(let [board (make-board 9 nil)]
			(should= false (row-won? board (get-rows board [[1 2 3]]) "x"))))
						
)

(run-specs)




