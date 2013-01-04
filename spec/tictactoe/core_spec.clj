(ns tictactoe.core-spec
  (:require [speclj.core :refer :all]
            [tictactoe.core :refer :all]))

(describe "Truth"

  (it "is true"
    (should (= (foo 2) 4)))

  (it "is not false"
    (should-not false)))

(run-specs)