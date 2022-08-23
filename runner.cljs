(ns runner
  (:require
    [promesa.core :as p]
    [promise-second-test]
    [promise-test]))

(defn -main
  [_]
  (-> (p/let [_ (promise-test/run-tests)
              _ (promise-second-test/run-tests)]
        (js/console.log "TESTS conclude"))
      (p/finally (fn [_ _]
                 (js/console.log "Finally TESTS conclude")))))
