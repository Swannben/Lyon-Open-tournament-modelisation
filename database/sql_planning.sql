/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de cr�ation :  19/12/2021 04:32:20                      */
/*==============================================================*/

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

drop table if exists equiperamassage;

drop table if exists arbitre;

drop table if exists nationalite;

/*==============================================================*/
/* Table : nationalite                                              */
/*==============================================================*/
create table nationalite
(
   idnationalite        serial not null,
   libelle              varchar(254),
   primary key (idnationalite)
);

/*==============================================================*/
/* Table : arbitre                                              */
/*==============================================================*/
create table arbitre
(
   idarbitre            serial not null,
   idnationalite        int,
   idutilisateur        int,
   certification        varchar(254),
   nbdematchfaits       int,
   nbdematchfaitd       int,
   nom                  varchar(254),
   prenom               varchar(254),
   primary key (idarbitre)
);

/*==============================================================*/
/* Table : court                                                */
/*==============================================================*/
create table court
(
   idcourt              serial not null,
   nomcourt             varchar(254),
   estprincipal         bool,
   primary key (idcourt)
);

/*==============================================================*/
/* Table : creneau                                              */
/*==============================================================*/
create table creneau
(
   jour                 date not null,
   heure                int not null,
   idmatch              int,
   idcourt              int,
   primary key (jour, heure)
);

/*==============================================================*/
/* Table : entrainement                                         */
/*==============================================================*/
create table entrainement
(
   identrainement       int not null,
   idjoueur             int not null,
   idcourt              int,
   heure                int,
   duree                int,
   jour                 date,
   primary key (identrainement)
);

/*==============================================================*/
/* Table : equipe                                               */
/*==============================================================*/
create table equipe
(
   idequipe             serial not null,
   primary key (idequipe)
);

/*==============================================================*/
/* Table : equiperamassage                                      */
/*==============================================================*/
create table equiperamassage
(
   idequiperam          serial not null,
   nomequipe            varchar(254),
   primary key (idequiperam)
);

/*==============================================================*/
/* Table : jouedouble                                           */
/*==============================================================*/
create table jouedouble
(
   idmatch              int not null,
   idequipe             int not null,
   primary key (idmatch, idequipe)
);

/*==============================================================*/
/* Table : jouesimple                                           */
/*==============================================================*/
create table jouesimple
(
   idmatch              int not null,
   idjoueur             int not null,
   primary key (idmatch, idjoueur)
);

/*==============================================================*/
/* Table : joueur                                               */
/*==============================================================*/
create table joueur
(
   idjoueur             serial not null,
   idequipe             int,
   idnationalite        int,
   nom                  varchar(254),
   prenom               varchar(254),
   idutilisateur        int,
   primary key (idjoueur)
);

/*==============================================================*/
/* Table : ligne                                                */
/*==============================================================*/
create table ligne
(
   idmatch              int not null,
   idarbitre            int not null,
   primary key (idmatch, idarbitre)
);

/*==============================================================*/
/* Table : "match"                                              */
/*==============================================================*/
create table "match"
(
   idmatch              serial not null,
   estqualif            bool,
   primary key (idmatch)
);

/*==============================================================*/
/* Table : matchdouble                                          */
/*==============================================================*/
create table matchdouble
(
   idmatch              int not null,
   idarbitre            int not null,
   primary key (idmatch)
);

/*==============================================================*/
/* Table : matchsimple                                          */
/*==============================================================*/
create table matchsimple
(
   idmatch              int not null,
   idarbitre            int not null,
   primary key (idmatch)
);

/*==============================================================*/
/* Table : ramassage                                            */
/*==============================================================*/
create table ramassage
(
   idequiperam          serial not null,
   idmatch              serial not null,
   primary key (idequiperam, idmatch)
);


alter table arbitre add constraint fk_arbitre_nationalite foreign key (idnationalite)
      references nationalite (idnationalite) on delete restrict on update restrict;

alter table arbitre add constraint fk_arbitre_utilisateur foreign key (idutilisateur)
      references utilisateur (idutilisateur) on delete restrict on update restrict;

alter table creneau add constraint fk_creneau_court foreign key (idcourt)
      references court (idcourt) on delete restrict on update restrict;

alter table creneau add constraint fk_creneau_match foreign key (idmatch)
      references "match" (idmatch) on delete restrict on update restrict;

alter table entrainement add constraint fk_entrainement_court foreign key (idcourt)
      references court (idcourt) on delete restrict on update restrict;

alter table entrainement add constraint fk_entrainement_joueur foreign key (idjoueur)
      references joueur (idjoueur) on delete restrict on update restrict;

alter table jouedouble add constraint fk_jouedouble_equipe foreign key (idequipe)
      references equipe (idequipe) on delete restrict on update restrict;

alter table jouedouble add constraint fk_jouedouble_matchdouble foreign key (idmatch)
      references matchdouble (idmatch) on delete restrict on update restrict;

alter table jouesimple add constraint fk_jouesimple_joueur foreign key (idjoueur)
      references joueur (idjoueur) on delete restrict on update restrict;

alter table jouesimple add constraint fk_jouesimple_matchsimple foreign key (idmatch)
      references matchsimple (idmatch) on delete restrict on update restrict;

alter table joueur add constraint fk_joueur_equipe foreign key (idequipe)
      references equipe (idequipe) on delete restrict on update restrict;

alter table joueur add constraint fk_joueur_nationalite foreign key (idnationalite)
      references nationalite (idnationalite) on delete restrict on update restrict;

alter table joueur add constraint fk_joueur_utilisateur foreign key (idutilisateur)
      references utilisateur (idutilisateur) on delete restrict on update restrict;
      
alter table ligne add constraint fk_ligne_arbitre foreign key (idarbitre)
      references arbitre (idarbitre) on delete restrict on update restrict;

alter table ligne add constraint fk_ligne_match foreign key (idmatch)
      references "match" (idmatch) on delete restrict on update restrict;

alter table matchdouble add constraint fk_matchdouble_arbitre foreign key (idarbitre)
      references arbitre (idarbitre) on delete restrict on update restrict;

alter table matchdouble add constraint fk_matchdouble_match foreign key (idmatch)
      references "match" (idmatch) on delete restrict on update restrict;

alter table matchsimple add constraint fk_matchsimple_match foreign key (idmatch)
      references "match" (idmatch) on delete restrict on update restrict;

alter table matchsimple add constraint fk_chaise foreign key (idarbitre)
      references arbitre (idarbitre) on delete restrict on update restrict;

alter table ramassage add constraint fk_ramassage_equiperamassage foreign key (idequiperam)
      references equiperamassage (idequiperam) on delete restrict on update restrict;

alter table ramassage add constraint fk_ramassage_match foreign key (idmatch)
      references "match" (idmatch) on delete restrict on update restrict;

