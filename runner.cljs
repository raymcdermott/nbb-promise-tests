(ns runner
  (:require
    [promise-test]
    [promesa.core :as p]))


(defn -main
  [_]
  (p/let [;; Run a test that sets conditions for other tests
          p1 (p/promise 1)]
    (when (= 1 p1)
      (-> (p/let [;; Run a test suite containing > 1 deftest
                  tests1 (promise-test/run-tests)]
            (js/console.log "TESTS conclude" tests1))
          (p/finally (fn [_ _]
                       (js/console.log "FINALLY Called Now")
                       (p/promise 1)))))))
