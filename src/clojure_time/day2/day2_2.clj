(ns clojure-time.day2.day2_2
  (:require [clojure.string :as string]
            [clojure.data :refer :all]
            [clojure.test :refer :all]
            [clojure.math.combinatorics :as c]))

(let [file-content (slurp "input")
      as-string-list (string/split-lines file-content)]

  (defn get-common-symbols [a b]
    (apply str (last (diff (seq a) (seq b)))))


  (defn get-result [input_list]
    (let [combs (c/combinations input_list 2)
          comm-strings (map #(apply get-common-symbols %) combs)
          sorted-com-strings (sort-by count comm-strings)
          shortest (last sorted-com-strings)]
      shortest))

  (deftest a-test
    (testing (is (= "fgij"
                    (get-result ["abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz",])))))
  (a-test)

  (println (str "Answer " (time (get-result as-string-list)))))