inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber property-vault glib-2.0 gbm camx adreno"

SRCREV = "587ec9a9723f9fe87921c998e1a53420dd3eb809"

SRC_URI = "git://qpm-git.qualcomm.com/home2/git/revision-history/qualcomm_linux-spf-1-0-le-qclinux-1-0-r1_api-linux_history_prebuilts.git;protocol=https;branch=LE.QCLINUX.1.0.R1"

PREBUILT_TARBALL = "chicdk_1.0_${PACKAGE_ARCH}.tar.gz"

S = "${WORKDIR}/git/apps_proc/prebuilt_HY22"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_package_qa[noexec] = "1"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/lib/rfsa/adsp/* \
    /usr/include/* \
    /lib/firmware/* \
    /system/etc/camera/*"

FILES:${PN}-dev = ""

INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} = "already-stripped"
