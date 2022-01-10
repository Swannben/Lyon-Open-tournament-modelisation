/*==============================================================*/
/* Table : utilisateur                                              */
/*==============================================================*/

drop table if exists utilisateur;

create table utilisateur
(
   idutilisateur        serial not null,
   email                varchar(254) not null,
   mdp                  varchar(254) not null,
   nom                  varchar(254) not null,
   typeutilisateur      int default -1,
   primary key (idutilisateur)
);

/* typeUtilisateur
 * 0 = Administrateur (tous les droits)
 * 1 = Gerant de l'hebergement
 * 2 = VIP
 * 3 = Responsable de l'hebergement du tournoi
 * 4 = Joueur
 * 5 = Arbitre
 * -1 = Rien
 */
