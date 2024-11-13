# Tâche 3 Rapport

******

Projet : [Makelangelo](https://github.com/umontreal-diro/Makelangelo-software)

Membres : David Qi 20250126 - Yilin Zang 20209818

Répertoire : [Lien](https://github.com/ansis10/Makelangelo-software) vers le répertoire.

Documentation : Suivante.

[Bonus](https://github.com/ansis10/Makelangelo-software/tree/master/.github/workflows/.lolcommits)

******

# Documentation des Changements Apportés à la GitHub Action

Nous avons mis à jour la GitHub Action utilisée pour l'exécution des tests Java afin de supporter l'exécution avec cinq flags JVM différents. Ces flags sont utilisés pour optimiser la gestion de la mémoire, les performances, l'observabilité, et la gestion des erreurs lors de l'exécution des tests. Cela permet une exécution plus robuste et informative des tests, ainsi que la collecte de données pour une analyse détaillée des performances. De plus, une matrice a été utilisée pour exécuter chaque flag de manière parallèle.

Pour la configuration de la distribution et de la version Java, nous avons implémenté une configuration plus simple et statique qui verrouille la configuration sur une version spécifique de Java et de distribution.

Nous définissons ensuite les options JVM que Maven utilisera (par exemple, pour la gestion de la mémoire ou la collecte de données sur les performances). Ensuite, nous construisons le projet avec Maven, exécutons les tests, générons un rapport de couverture avec JaCoCo, et incluons la possibilité de lancer des tests JavaFX dans un environnement sans affichage grâce à `xvfb-run`.

Enfin, nous nous sommes inspirés du fichier `test.yml` du projet crypto, qui vérifie si la couverture des tests a augmenté par rapport à un seuil spécifié. Si la couverture n'est pas améliorée, l'étape échoue. 

Un message d’humour est situé à la fin du fichier `test.yml`.
Lien du fichier `test.yml` : https://github.com/ansis10/Makelangelo-software/blob/master/.github/workflows/test.yml

## Gestion de Mémoire

`-Xms1024m -Xmx2048m`  
Définit la taille initiale et maximale du tas mémoire pour la JVM. Cela garantit qu'assez de mémoire est allouée à la JVM pour exécuter les tests, sans trop de limitations ni de surallocation. Idéal pour des tests de performance ou si on a beaucoup de tests consommateurs de mémoire.

## Performance

`-XX:+UseG1GC`  
Utilise le garbage collector G1, qui est adapté aux applications ayant de grandes quantités de mémoire. Cela peut être utile si on a des tests qui impliquent de lourdes allocations de mémoire ou qui s'exécutent pendant un temps prolongé.

`-XX:+PrintGCDetails`  
Active les logs détaillés du garbage collector. Utile pour observer comment la JVM gère la mémoire pendant les tests, ce qui peut être pertinent si on veut surveiller l'impact du garbage collection sur la performance des tests.

## Observabilité

`-Duser.timezone=UTC`  
Définit le fuseau horaire de la JVM à UTC, ce qui peut être utile pour garantir la cohérence des résultats de tests qui dépendent de l'heure ou des dates. Cela permet d'éviter des problèmes liés aux différences de fuseaux horaires.

## Gestion des Erreurs

`-XX:+ShowMessageBoxOnError`  
Affiche une boîte de message lorsque la JVM rencontre une erreur. Cela peut être utile pour repérer rapidement des erreurs dans l'exécution des tests.

## Exécution

`-Djava.awt.headless=true`  
Exécute la JVM en mode "headless", ce qui est particulièrement important si on fait des tests dans des environnements de serveur ou d'intégration continue (CI/CD), où il n'y a pas d'interface graphique.

## Documentation

1. https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/jvm-options-java-parameters-command-line-environment-variable-list-xms-xmx-memory
2. https://www.oracle.com/technical-resources/articles/javase/headless.html
3. https://docs.oracle.com/javase/9/troubleshoot/time-zone-settings-jre.htm#GUID-6FE66D13-4C13-452E-BABE-8805876FE6C5
4. https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/clopts001.html

## Merci :)