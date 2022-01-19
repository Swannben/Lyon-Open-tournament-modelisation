/*==============================================================*/
/* Nom de SGBD :  PostgreSQL                                    */
/* Date de création :  19/12/2021 04:32:20                      */
/*==============================================================*/

-- Suppression ------------------------------------------------------

-- Planning -------------------------------------

drop table if exists creneau;

drop table if exists entrainement;

drop table if exists court;

drop table if exists jouedouble;

drop table if exists jouesimple;

drop table if exists joueur;

drop table if exists equipe;

drop table if exists ligne;

drop table if exists ramassage;

drop table if exists matchdouble;

drop table if exists matchsimple;

drop table if exists "match";

drop table if exists "set";

drop table if exists equiperamassage;

drop table if exists arbitre;


-- Hébergement ----------------------------------

drop table if exists demande;

drop table if exists hotelproposeservice;

drop table if exists reservation;

drop table if exists chambre;

drop table if exists "service";

drop table if exists typechambre;

drop table if exists hotel;


-- Commun ---------------------------------------

drop table if exists vip;

drop table if exists utilisateur;

drop table if exists nationalite;


-- Création ---------------------------------------------------------

-- Commun ---------------------------------------

create table nationalite
(
   idnationalite        serial not null,
   libelle              varchar(254),
   primary key (idnationalite)
);


create table utilisateur
(
   idutilisateur        serial not null,
   email                varchar(254) not null,
   mdp                  varchar(254) not null,
   nom                  varchar(254) not null,
   typeutilisateur      int default -1,
/* 0 = Administrateur (tous les droits)
 * 1 = Gerant de l'hebergement
 * 2 = VIP
 * 3 = Responsable de l'hebergement du tournoi
 * 4 = Joueur
 * 5 = Arbitre
 * -1 = Rien
 */
   primary key (idutilisateur)
);

create table vip
(
   idvip                serial not null,
   accompagne           int,
   idutilisateur        int,
   nom                  varchar(254),
   prenom               varchar(254),
   numerotel            int,
   typevip              int,
/* 0 = rien
 * 
 */
   idnationalite          int,
   primary key (idvip)
);


-- Hébergement ----------------------------------

create table chambre
(
   idchambre            serial not null,
   idhotel              int not null,
   idtypechambre        int not null,
   numchambre           int,
   debutplagedispo      date,
   finplagedispo        date,
   primary key (idchambre)
);

create table demande
(
   iddemande            serial not null,
   idhotel              int not null,
   idtypechambre        int not null,
   demandeur            int not null,
   accompagnants        varchar(254),
   etat                 int,
   primary key (iddemande)
);

create table hotel
(
   idhotel              serial not null,
   nom                  varchar(254),
   adresse              varchar(254),
   nbetoiles            int,
   "url"                varchar(254),
   idutilisateur        int,
   primary key (idhotel)
);

create table hotelproposeservice
(
   idservice            int not null,
   idhotel              int not null,
   primary key (idservice, idhotel)
);

create table reservation
(
   idchambre            int not null,
   idvip                int not null,
   datearrivee          date,
   datedepart           date,
   primary key (idvip)
);

create table "service"
(
   idservice            serial not null,
   libelle              varchar(254),
   primary key (idservice)
);

create table typechambre
(
   idhotel              int not null,
   idtypechambre        serial not null,
   intitule             varchar(254),
   suite                bool,
   nblitss              int,
   nblitsd              int,
   description          varchar(254),
   url                  varchar(254),
   nbchambres           int,
   primary key (idhotel, idtypechambre)
);


-- Planning -------------------------------------

create table arbitre
(
   idarbitre            int not null,     -- idvip
   certification        varchar(254),
   nbdematchfaits       int,
   nbdematchfaitd       int,
   primary key (idarbitre)
);

create table court
(
   idcourt              serial not null,
   nomcourt             varchar(254),
   estprincipal         bool,
   primary key (idcourt)
);

create table creneau
(
   jour                 date not null,
   heure                int not null,
   idmatch              int,
   idcourt              int,
   primary key (jour, heure)
);

create table entrainement
(
   identrainement       serial not null,
   idjoueur             int not null,
   idcourt              int,
   heure                int,
   duree                int,
   jour                 date,
   primary key (identrainement)
);

create table equipe
(
   idequipe             serial not null,
   primary key (idequipe)
);

create table equiperamassage
(
   idequiperam          serial not null,
   nomequipe            varchar(254),
   primary key (idequiperam)
);

create table jouedouble
(
   idmatch              int not null,
   idequipe             int not null,
   primary key (idmatch, idequipe)
);

create table jouesimple
(
   idmatch              int not null,
   idjoueur             int not null,
   primary key (idmatch, idjoueur)
);

create table joueur
(
   idjoueur             int not null,     -- idvip
   idequipe             int,
   estqualifie          boolean,
   primary key (idjoueur)
);

create table ligne
(
   idmatch              int not null,
   idarbitre            int not null,
   primary key (idmatch, idarbitre)
);

create table "set"
(
   idset                serial not null,
   jeux1                int,
   jeux2                int,
   pointsdernierjeu1    int,
   pointsdernierjeu2    int,
   primary key (idset)
);

create table "match"
(
   idmatch              serial not null,
   set1                 int,
   set2                 int,
   set3                 int,
   set4                 int,
   set5                 int,
   primary key (idmatch)
);

create table matchdouble
(
   idmatch              int not null,
   idarbitre            int,
   primary key (idmatch)
);

create table matchsimple
(
   idmatch              int not null,
   estqualif            boolean,
   idarbitre            int,
   primary key (idmatch)
);

create table ramassage
(
   idequiperam          serial not null,
   idmatch              serial not null,
   primary key (idequiperam, idmatch)
);



-- Clefs étrangères -------------------------------------------------

-- Commun ---------------------------------------

alter table vip add constraint fk_vip_vip foreign key (accompagne)
      references vip (idvip) on delete set null on update set null;

alter table vip add constraint fk_vip_utilisateur foreign key (idutilisateur)
      references utilisateur (idutilisateur) on delete set null on update set null;


-- Hébergement ----------------------------------

alter table vip add constraint fk_vip_nationalite foreign key (idnationalite)
      references nationalite (idnationalite) on delete set null on update cascade;

alter table chambre add constraint fk_chambre_typechambre foreign key (idhotel, idtypechambre)
      references typechambre (idhotel, idtypechambre) on delete restrict on update cascade;

alter table demande add constraint fk_demande_typechambre foreign key (idhotel, idtypechambre)
      references typechambre (idhotel, idtypechambre) on delete restrict on update cascade;

alter table demande add constraint fk_demande_vip foreign key (demandeur)
      references vip (idvip) on delete restrict on update cascade;

alter table hotel add constraint fk_hotel_utilisateur foreign key (idutilisateur)
      references utilisateur (idutilisateur) on delete set null on update cascade;

alter table hotelproposeservice add constraint fk_hotelproposeservice_hotel foreign key (idhotel)
      references hotel (idhotel) on delete cascade on update cascade;

alter table hotelproposeservice add constraint fk_hotelproposeservice_service foreign key (idservice)
      references service (idservice) on delete cascade on update cascade;

alter table reservation add constraint fk_reservation_chambre foreign key (idchambre)
      references chambre (idchambre) on delete restrict on update cascade;

alter table reservation add constraint fk_reservation_vip foreign key (idvip)
      references vip (idvip) on delete restrict on update cascade;

alter table typechambre add constraint fk_typechambre_hotel foreign key (idhotel)
      references hotel (idhotel) on delete cascade on update cascade;


-- Planning -------------------------------------

alter table arbitre add constraint fk_arbitre_vip foreign key (idarbitre)
      references vip (idvip) on delete cascade on update cascade;

alter table creneau add constraint fk_creneau_court foreign key (idcourt)
      references court (idcourt) on delete restrict on update cascade;

alter table creneau add constraint fk_creneau_match foreign key (idmatch)
      references "match" (idmatch) on delete restrict on update cascade;

alter table entrainement add constraint fk_entrainement_court foreign key (idcourt)
      references court (idcourt) on delete restrict on update cascade;

alter table entrainement add constraint fk_entrainement_joueur foreign key (idjoueur)
      references joueur (idjoueur) on delete cascade on update cascade;

alter table jouedouble add constraint fk_jouedouble_equipe foreign key (idequipe)
      references equipe (idequipe) on delete cascade on update cascade;

alter table jouedouble add constraint fk_jouedouble_matchdouble foreign key (idmatch)
      references matchdouble (idmatch) on delete cascade on update cascade;

alter table jouesimple add constraint fk_jouesimple_joueur foreign key (idjoueur)
      references joueur (idjoueur) on delete cascade on update cascade;

alter table jouesimple add constraint fk_jouesimple_matchsimple foreign key (idmatch)
      references matchsimple (idmatch) on delete cascade on update cascade;

alter table joueur add constraint fk_joueur_equipe foreign key (idequipe)
      references equipe (idequipe) on delete set null on update cascade;

alter table joueur add constraint fk_joueur_vip foreign key (idjoueur)
      references vip (idvip) on delete cascade on update cascade;
      
alter table ligne add constraint fk_ligne_arbitre foreign key (idarbitre)
      references arbitre (idarbitre) on delete cascade on update cascade;

alter table ligne add constraint fk_ligne_match foreign key (idmatch)
      references "match" (idmatch) on delete cascade on update cascade;

alter table "match" add constraint fk_match_set1 foreign key (set1)
      references "set" (idset) on delete set null on update cascade;

alter table "match" add constraint fk_match_set2 foreign key (set2)
      references "set" (idset) on delete set null on update cascade;

alter table "match" add constraint fk_match_set3 foreign key (set3)
      references "set" (idset) on delete set null on update cascade;

alter table "match" add constraint fk_match_set4 foreign key (set4)
      references "set" (idset) on delete set null on update cascade;

alter table "match" add constraint fk_match_set5 foreign key (set5)
      references "set" (idset) on delete set null on update cascade;

alter table matchdouble add constraint fk_matchdouble_arbitre foreign key (idarbitre)
      references arbitre (idarbitre) on delete set null on update cascade;

alter table matchdouble add constraint fk_matchdouble_match foreign key (idmatch)
      references "match" (idmatch) on delete cascade on update cascade;

alter table matchsimple add constraint fk_matchsimple_match foreign key (idmatch)
      references "match" (idmatch) on delete cascade on update cascade;

alter table matchsimple add constraint fk_chaise foreign key (idarbitre)
      references arbitre (idarbitre) on delete set null on update cascade;

alter table ramassage add constraint fk_ramassage_equiperamassage foreign key (idequiperam)
      references equiperamassage (idequiperam) on delete cascade on update cascade;

alter table ramassage add constraint fk_ramassage_match foreign key (idmatch)
      references "match" (idmatch) on delete cascade on update cascade;

