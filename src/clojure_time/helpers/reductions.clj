(ns clojure-time.helpers.reductions)


(def seq1 [1, 2, 3, -5, 4, 1, 2, 5, 6, 7, -25,])
(println (reductions + seq1))
;; -- find duplicates in intermediate values of sum of seq ---------------
(def result
  (filter #(< 1 (val %)) (frequencies (reductions + seq1))))

(println result)

;; -- get last duplicated intermediate values of sum of seq ---------------
(println "--------------\n")
(def result (last (keys (filter #(< 1 (val %)) (frequencies (reductions + seq1))))))
(println result)

;; ------ get 100 elements of repeated sequence -----------
(def result  (take 100 (flatten (repeat seq1))))
(println result)

;; ------ get 100 elements of repeated sequence -----------
(println "--------------\n")
(def elements  (flatten (repeat 10 seq1)))

(def reducts (reductions + elements))
(println reducts)

(def freqs (frequencies reducts))
(println freqs)

(def result  (first (filter #(> (val %) 1) freqs)))

(println result)
